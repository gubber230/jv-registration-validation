package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.exceptions.RegistrationException;
import core.basesyntax.model.User;
import core.basesyntax.service.RegistrationServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RegistrationServiceImplTest {
    private static List<User> users;
    private final RegistrationServiceImpl util = new RegistrationServiceImpl();

    @BeforeAll
    static void setUp() {
        users = new ArrayList<>();
        users.add(new User("SuperAdam", "123456", 19));
        users.add(new User("SuperAdam", "randomPassword", 99));
        Storage.people.add(users.get(0));
    }

    @Test
    void register_notUniqueLogin_NotOK() {
        assertThrows(RegistrationException.class, () -> {
            util.register(users.get(1));
        });
    }

    @Test
    void register_nullLogin_NotOK() {
        assertThrows(RegistrationException.class, () -> {
            util.register(new User(null, "123456", 123456));
        });
    }

    @Test
    void register_shortLogin_NotOK() {
        assertThrows(RegistrationException.class, () -> {
            util.register(new User("1", "123456", 123456));
        });
    }

    @Test
    void register_nullPassword_NotOK() {
        assertThrows(RegistrationException.class, () -> {
            util.register(new User("123456", null, 123456));
        });
    }

    @Test
    void register_shortPassword_NotOK() {
        assertThrows(RegistrationException.class, () -> {
            util.register(new User("123456", "1", 123456));
        });
    }

    @Test
    void register_nullAge_NotOK() {
        assertThrows(RegistrationException.class, () -> {
            util.register(new User("123456", "123456", null));
        });
    }

    @Test
    void register_ageUnder18_NotOK() {
        assertThrows(RegistrationException.class, () -> {
            util.register(new User("123456", "123456", 1));
        });
    }
}
