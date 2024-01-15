package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //리소스 낭비를 줄이고 단순히 읽기용 모드로 사용 지정. 조회시에는 가급적 readOnly = true 를 넣어준다
@RequiredArgsConstructor //파이널만 자동으로 생성자 만들어줌
public class MemberService {

    private final MemberRepository memberRepository; //컴파일 시점에 체크할 수 있도록 final 추가 추천

    // 생성자 인젝션 (중간에 리포지토리를 바꿀 수 없음, @Autowire 어노테이션 생략가능)
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // setter 인젝션 -> 한번 서버가 실행되면 의존을 바꿀일이 없기 때문에 요즘은 많이 쓰지않는 방법임
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        //중복회원 검증
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        //멀티스레드 등 만일의 상황을 고려해서 데이터베이스 멤버 이름을 유니크 제약을 걸어두는게 좋다.
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
