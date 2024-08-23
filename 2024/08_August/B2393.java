import java.io.*;
public class Main {
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        addString("  ___  ___  ___");
        addString("  | |__| |__| |");
        addString("  |           |");
        addString("   \\_________/");
        addString("    \\_______/");
        addString("     |     |");
        addString("     |     |");
        addString("     |     |");
        addString("     |     |");
        addString("     |_____|");
        addString("  __/       \\__");
        addString(" /             \\");
        addString("/_______________\\");

        System.out.println(sb);
    }

    static void addString(String str){
        sb.append(str).append("\n");
    }
}