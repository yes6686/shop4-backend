    package com.example.shop4.service.impl;

    import com.example.shop4.dto.MemberCouponDto;
    import com.example.shop4.entity.Coupons;
    import com.example.shop4.entity.Member;
    import com.example.shop4.entity.MemberCoupon;
    import com.example.shop4.exception.ResourceNotFoundException;
    import com.example.shop4.mapper.MemberCouponMapper;
    import com.example.shop4.repository.CouponsRepository;
    import com.example.shop4.repository.MemberCouponRepository;
    import com.example.shop4.repository.MemberRepository;
    import com.example.shop4.service.MemberCouponService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.time.LocalDate;
    import java.util.Collections;
    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    public class MemberCouponServiceImpl implements MemberCouponService {
        @Autowired
        private MemberCouponRepository memberCouponRepository;
        @Autowired
        private CouponsRepository couponsRepository;
        @Autowired
        private MemberRepository memberRepository;

        @Override
        public MemberCouponDto distributeCouponToUser(Long userId, Long couponId) {

            // 유저 정보 찾기
            Member member = memberRepository.findById(userId).orElseThrow(
                    ()->new ResourceNotFoundException("Member not found with id : " + userId)
            );

            // 쿠폰 정보 찾기
            Coupons coupon = couponsRepository.findById(couponId).orElseThrow(
                    ()->new ResourceNotFoundException("Coupon not found with id : " + couponId)
            );

            // 매핑 테이블에 유저,쿠폰이 이미 있다면 개수만 증가 + 사용 여부가 같다면


            // 유저, 쿠폰이 존재하면
            MemberCoupon memberCoupon = new MemberCoupon();

            // 매핑 엔티티에 저장하고
            memberCoupon.setMember(member);

            //쿠폰 개수가 있는지 1개 이상인지 확인
            if(coupon.getCount() > 0){
                // 쿠폰 개수가 1개 이상이면 부여
                memberCoupon.setCoupons(coupon);
                memberCoupon.setUsedCoupon(false); // 새로 부여된 쿠폰은 사용되지 않은 상태
                memberCoupon.setUsedDate(null); // 사용 날짜는 null로 설정

                // 쿠폰 개수 감소
                coupon.setCount(coupon.getCount() - 1);
                couponsRepository.save(coupon); // 변경된 쿠폰 정보 저장

                // MemberCoupon 저장
                MemberCoupon savedMemberCoupon = memberCouponRepository.save(memberCoupon);

                // DTO로 변환하여 반환
                return MemberCouponMapper.mapToMemberCouponDto(memberCoupon);

            }
            //쿠폰이 없으면 오류 반환
            else {
                throw new ResourceNotFoundException("Coupon is not available for distribution.");
            }
        }

        @Override
        public MemberCouponDto useCoupon(Long mapId) {
            // 매핑된 쿠폰이 있는지 확인
            MemberCoupon memberCoupon = memberCouponRepository.findById(mapId).orElseThrow(
                    ()->new ResourceNotFoundException("MemberCouponMap not found with id : " + mapId)
            );

            // 아직 사용하지 않았는지 확인!
            if(!memberCoupon.isUsedCoupon()){
                // Coupons에 있는 limitDate 값을 가져옴
                LocalDate limitDate = memberCoupon.getCoupons().getLimitDate();

                // limitDate와 현재 날짜를 비교
                if (limitDate.isAfter(LocalDate.now())) {
                    // 쿠폰 사용 로직 처리
                    memberCoupon.setUsedCoupon(true);
                    memberCoupon.setUsedDate(LocalDate.now());
                } else {
                    throw new ResourceNotFoundException("쿠폰의 유효기간이 만료되었습니다.");
                }
            }
            else {
                throw new ResourceNotFoundException("사용된 쿠폰입니다.");
            }
            MemberCoupon savedMemberCoupon = memberCouponRepository.save(memberCoupon);
            return MemberCouponMapper.mapToMemberCouponDto(savedMemberCoupon);
        }

        @Override
        public List<MemberCouponDto> getUserCoupons(Long userId) {
            // 유저가 쿠폰이 있는지 확인
            List<MemberCoupon> memberCoupons = memberCouponRepository.findByMemberId(userId);

            //유저가 쿠폰이 없다면 null로 리턴
            if(memberCoupons.isEmpty()){
                System.out.println("ㅎㅎ");
                return Collections.emptyList(); // null 대신 빈 리스트 반환
            }

            // 사용되지 않은 쿠폰 필터링 후 MemberCouponDto로 변환하여 반환
            return memberCoupons.stream()
                    .map(memberCoupon -> MemberCouponMapper.mapToMemberCouponDto(memberCoupon)) // MemberCouponDto로 변환
                    .collect(Collectors.toList());
        }
    }
