package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional //위에 리드온리로 걸려있으니 오버라이드 되지 않도록 save는 별도로 걸어준다.
    public void save(Item item) {
        itemRepository.save(item);
    }

    // 준영속 엔티티 : 영속성 컨텍스트가 더는 관리하지 않는 엔티티
    // 준영속 엔티티를 수정하는 2가지 방법 : 변경 감지 기능 사용, 병합( merge ) 사용


    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
