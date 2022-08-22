package core;

import utilities.ReadConfig;

public class TestManager {

    private static TestManager inst = null;
    
    ReadConfig readConfig = new ReadConfig();

    private String username;
    private String password;

    private TestManager() {
        fetchTestParams();
    }

    private void fetchTestParams() {
        username = readConfig.getUsername();
        password = readConfig.getPassword();
    }

    public static TestManager getInstance() {
        if (inst == null) {
            inst = new TestManager();
        }
        return inst;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}