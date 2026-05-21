package net.dante;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.dante.items.Magazine;

@RestController
@RequestMapping("/magazines")
public class MagazineController extends ItemController<Magazine> {
    public MagazineController(DataStore dataStore) {
        super(dataStore::getMagazines, dataStore::addMagazine);
    }
}
