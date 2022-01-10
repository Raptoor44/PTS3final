package com.example.pts3.Activity_static;

import com.example.pts3.activiteesImportantes.MainActivity;

public class MainActivity_static {

    private static MainActivity main;

    public static MainActivity getMain() {
        return main;
    }

    public static void setMain(MainActivity main) {
        MainActivity_static.main = main;
    }


}
