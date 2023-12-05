package fift.server.repository.gifticon;

import fift.server.domain.gifticon.Gifticon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GifticonRepository extends JpaRepository<Gifticon, Long> {
}
