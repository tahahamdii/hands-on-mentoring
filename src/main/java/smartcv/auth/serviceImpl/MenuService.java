package smartcv.auth.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartcv.auth.menu.Menu;
import smartcv.auth.menu.MenuRepository;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // Method to get all menus
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu addMenu(Menu menu) {
        return menuRepository.save(menu);
    }
}
