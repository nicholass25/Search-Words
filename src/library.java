import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.time.Duration;

public class library {

    public static char[][] readMatrix(List<String> words) {
        try {
            Scanner File = new Scanner(new File("../Word Search/file/input.txt"));
            List<String> file = new ArrayList<>();

            while (File.hasNextLine()) {

                String Str = File.nextLine();
                Str = Str.replaceAll(" ", "");
                file.add(Str);
            }
            int i;
            for (i = 0; i < file.size(); i++) {
                if (file.get(i).length() == 0) {
                    break;
                }
            }
            List<String> temp = file.subList(0, i);
            List<String> temp2 = file.subList(i + 1, file.size());

            for (String b : temp2) {
                words.add(b);
            }

            int row = temp.size();
            int col = temp.get(1).length();
            char[][] matrix = new char[row][col];

            for (int c = 0; c < row; c++) {
                for (int d = 0; d < col; d++) {
                    matrix[c][d] = temp.get(c).charAt(d);
                }
            }

            return matrix;

        } catch (FileNotFoundException e) {
            return new char[1][1];
        }

    }

    static void reverse(char a[]) {
        for (int i = 0; i < a.length / 2; i++) {
            char temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
    }

    public static char[] SToA(String a) {
        char[] ch = new char[a.length()];
        for (int i = 0; i < a.length(); i++) {
            ch[i] = a.charAt(i);
        }
        return ch;
    }

    public static boolean compareStrings(String a, String b) {
        char arrA[] = SToA(a);
        char arrB[] = SToA(b);
        int i = 0;
        boolean bool = false;
        while ((i <= a.length() - b.length()) && (!bool)) {
            int j = 0;
            while ((j <= b.length() - 1) && (arrB[j] == arrA[i + j])) {
                j += 1;
            }
            if (j == b.length()) {
                bool = true;
            } else {
                i += 1;
            }
        }
        return bool;
    }

    public static String foundString(char[][] matrix, int x, int y, int dir) {
        if (dir == 0) {
            String StrOut = new String();
            while (y >= 0) {
                StrOut += (matrix[y][x]);
                y--;
            }
            return StrOut;
        } else if (dir == 1) {
            String StrOut = new String();
            while (x < matrix[0].length && y >= 0) {
                StrOut += (matrix[y][x]);
                x++;
                y--;
            }
            return StrOut;
        } else if (dir == 2) {
            String StrOut = new String();
            while (x < matrix[0].length) {
                StrOut += (matrix[y][x]);
                x++;
            }
            return StrOut;
        } else if (dir == 3) {
            String StrOut = new String();
            while (x < matrix[0].length && y < matrix.length) {
                StrOut += (matrix[y][x]);
                x++;
                y++;
            }
            return StrOut;
        } else if (dir == 4) {
            String StrOut = new String();
            while (y < matrix.length) {
                StrOut += (matrix[y][x]);
                y++;
            }
            return StrOut;
        } else if (dir == 5) {
            String StrOut = new String();
            while (x >= 0 && y < matrix.length) {
                StrOut += (matrix[y][x]);
                x--;
                y++;
            }
            return StrOut;
        } else if (dir == 6) {
            String StrOut = new String();
            while (x >= 0) {
                StrOut += (matrix[y][x]);
                x--;
            }
            return StrOut;
        } else if (dir == 7) {
            String StrOut = new String();
            while (x >= 0 && y >= 0) {
                StrOut += (matrix[y][x]);
                x--;
                y--;
            }
            return StrOut;
        } else {
            String StrOut = " ";
            return StrOut;
        }

    }

    public static boolean foundWord(char[][] matrix, String word) {
        int i, j, k;
        for (i = 0; i < matrix[0].length; i++) {
            for (j = 0; j < matrix.length; j++) {
                for (k = 0; k < 7; k++) {
                    if (compareStrings(foundString(matrix, i, j, k), word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String[] AtL(List<String> words) {
        String[] arr = words.toArray(new String[0]);
        return arr;
    }

    public static int count(String a, String b) {
        char[] char1 = new char[a.length()];
        char[] char2 = new char[b.length()];
        for (int x = 0; x < a.length(); x++) {
            char1[x] = a.charAt(x);
        }
        for (int y = 0; y < b.length(); y++) {
            char2[y] = b.charAt(y);
        }
        int i, j;
        int count = 1;
        for (i = 0; i < char1.length - char2.length + 1; i++) {
            if (char1[i] == char2[0]) {
                for (j = 1; j < char2.length; j++) {
                    if (char1[i + j] == char2[j]) {
                        count++;
                    }
                }
                if (count == b.length()) {
                    return i;
                }
            }
        }
        return 0;
    }

    public static int[] getStringLocation(int time) {
        List<String> words = new ArrayList<>();
        char[][] Matrix = readMatrix(words);
        String[] obj = AtL(words);
        int[] Out = new int[4];
        int i = 0, j = 0, k = 0;

        while (i < Matrix[0].length) {
            while (j < Matrix.length) {
                while (k < 8) {
                    if (compareStrings(foundString(Matrix, i, j, k), obj[time])) {
                        if (k == 0) {
                            Out[0] = i;
                            Out[1] = j - count(foundString(Matrix, i, j, k), obj[time]);
                            Out[2] = k;
                            Out[3] = obj[time].length();
                            return Out;
                        }
                        if (k == 1) {
                            Out[0] = i + count(foundString(Matrix, i, j, k), obj[time]);
                            Out[1] = j - count(foundString(Matrix, i, j, k), obj[time]);
                            Out[2] = k;
                            Out[3] = obj[time].length();
                            return Out;
                        }
                        if (k == 2) {
                            Out[0] = i + count(foundString(Matrix, i, j, k), obj[time]);
                            Out[1] = j;
                            Out[2] = k;
                            Out[3] = obj[time].length();
                            return Out;
                        }
                        if (k == 3) {
                            Out[0] = i + count(foundString(Matrix, i, j, k), obj[time]);
                            Out[1] = j + count(foundString(Matrix, i, j, k), obj[time]);
                            Out[2] = k;
                            Out[3] = obj[time].length();
                            return Out;
                        }
                        if (k == 4) {
                            Out[0] = i;
                            Out[1] = j + count(foundString(Matrix, i, j, k), obj[time]);
                            Out[2] = k;
                            Out[3] = obj[time].length();
                            return Out;
                        }
                        if (k == 5) {
                            Out[0] = i - count(foundString(Matrix, i, j, k), obj[time]);
                            Out[1] = j + count(foundString(Matrix, i, j, k), obj[time]);
                            Out[2] = k;
                            Out[3] = obj[time].length();
                            return Out;
                        }
                        if (k == 6) {
                            Out[0] = i - count(foundString(Matrix, i, j, k), obj[time]);
                            Out[1] = j;
                            Out[2] = k;
                            Out[3] = obj[time].length();
                            return Out;
                        }
                        if (k == 7) {
                            Out[0] = i - count(foundString(Matrix, i, j, k), obj[time]);
                            Out[1] = j - count(foundString(Matrix, i, j, k), obj[time]);
                            Out[2] = k;
                            Out[3] = obj[time].length();
                            return Out;
                        }

                    }
                    k++;
                }
                j++;
                k = 0;
            }

            i++;
            j = 0;
            k = 0;
        }

        Out[0] = -1;
        Out[1] = -1;
        Out[2] = -1;
        Out[3] = -1;
        return Out;
    }

    public static char[][] wordToMatrix(char[][] Matrix, int[] Location, String word) {
        char[] ch = word.toCharArray();
        int i = 0, j = 0, k = 0, l = 0, m = 0;
        char[][] Mout = new char[Matrix.length][Matrix[0].length];
        if (Location[2] == 0) {
            reverse(ch);
            while (i < Matrix.length) {
                while (j < Matrix[0].length) {
                    if (j == Location[0] && i == Location[1] - Location[3] + 1 + l && m < Location[3]) {
                        Mout[i][j] = ch[m];
                        j++;
                        l++;
                        m++;
                    } else {
                        Mout[i][j] = '-';
                        j++;
                    }
                }
                i++;
                j = 0;
            }
        }
        if (Location[2] == 1) {
            reverse(ch);
            while (i < Matrix.length) {
                while (j < Matrix[0].length) {
                    if (j == Location[0] + Location[3] - 1 - k && i == Location[1] - Location[3] + 1 + l
                            && m < Location[3]) {
                        Mout[i][j] = ch[m];
                        j++;
                        k++;
                        l++;
                        m++;
                    } else {
                        Mout[i][j] = '-';
                        j++;
                    }
                }
                i++;
                j = 0;
            }
        }
        if (Location[2] == 2) {
            while (i < Matrix.length) {
                while (j < Matrix[0].length) {
                    if (j == Location[0] + k && i == Location[1] && m < Location[3]) {
                        Mout[i][j] = ch[m];
                        j++;
                        k++;
                        m++;
                    } else {
                        Mout[i][j] = '-';
                        j++;
                    }
                }
                i++;
                j = 0;
            }
        }
        if (Location[2] == 3) {
            while (i < Matrix.length) {
                while (j < Matrix[0].length) {
                    if (j == Location[0] + k && i == Location[1] + l && m < Location[3]) {
                        Mout[i][j] = ch[m];
                        j++;
                        k++;
                        l++;
                        m++;
                    } else {
                        Mout[i][j] = '-';
                        j++;
                    }
                }
                i++;
                j = 0;
            }
        }
        if (Location[2] == 4) {
            while (i < Matrix.length) {
                while (j < Matrix[0].length) {
                    if (j == Location[0] && i == Location[1] + l && m < Location[3]) {
                        Mout[i][j] = ch[m];
                        j++;
                        l++;
                        m++;
                    } else {
                        Mout[i][j] = '-';
                        j++;
                    }
                }
                i++;
                j = 0;
            }
        }
        if (Location[2] == 5) {
            while (i < Matrix.length) {
                while (j < Matrix[0].length) {
                    if (j == Location[0] - k && i == Location[1] + l
                            && m < Location[3]) {
                        Mout[i][j] = ch[m];
                        j++;
                        k++;
                        l++;
                        m++;
                    } else {
                        Mout[i][j] = '-';
                        j++;
                    }
                }
                i++;
                j = 0;
            }
        }
        if (Location[2] == 6) {
            reverse(ch);
            while (i < Matrix.length) {
                while (j < Matrix[0].length) {
                    if (j == Location[0] - Location[3] + k + 1 && i == Location[1] && m < Location[3]) {
                        Mout[i][j] = ch[m];
                        j++;
                        k++;
                        m++;
                    } else {
                        Mout[i][j] = '-';
                        j++;
                    }
                }
                i++;
                j = 0;
            }
        }
        if (Location[2] == 7) {
            reverse(ch);
            while (i < Matrix.length) {
                while (j < Matrix[0].length) {
                    if (j == Location[0] - Location[3] + 1 + l && i == Location[1] - Location[3] + 1 + k
                            && m < Location[3]) {
                        Mout[i][j] = ch[m];
                        j++;
                        k++;
                        l++;
                        m++;
                    } else {
                        Mout[i][j] = '-';
                        j++;
                    }
                }
                i++;
                j = 0;
            }
        }
        return Mout;
    }

    public static void writePerLine(String filename, String[] String, Duration time) {
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
                for (int i = 0; i < String.length; i++) {
                    bw.write(String[i]);
                    bw.newLine();
                }
                String tm = time.toString();
                char[] ch = new char[tm.length()];
                for (int i = 0; i < tm.length(); i++) {
                    ch[i] = tm.charAt(i);
                }
                char[] timing = new char[6];
                for (int j = 0; j < 6; j++) {
                    timing[j] = ch[j + 2];
                }

                String SS = new String(timing);
                bw.write(SS + " second");

                bw.flush();
            }
        } catch (IOException e1) {
        }
    }

    public static List<String> perString() {

        List<String> words = new ArrayList<>();
        List<String> Lout = new ArrayList<>();
        char[][] Matrix = readMatrix(words);
        int i, j;
        for (i = 0; i < words.size(); i++) {
            char[][] Mout = (wordToMatrix(Matrix, getStringLocation(i), AtL(words)[i]));
            for (j = 0; j < Matrix.length; j++) {
                String temp = new String(Mout[j]).replaceAll(".(?!$)", "$0 ");
                Lout.add(temp);
            }
            Lout.add(" ");
        }
        return Lout;
    }
}