package com.array;

import java.util.Arrays;
import java.util.HashMap;

public class IsValidSudoku {
    /**
     * 有效的数独
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * <p>
     * <p>
     * 上图是一个部分填充的有效的数独。
     * <p>
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * [
     * ["5","3",".",".","7",".",".",".","."],
     * ["6",".",".","1","9","5",".",".","."],
     * [".","9","8",".",".",".",".","6","."],
     * ["8",".",".",".","6",".",".",".","3"],
     * ["4",".",".","8",".","3",".",".","1"],
     * ["7",".",".",".","2",".",".",".","6"],
     * [".","6",".",".",".",".","2","8","."],
     * [".",".",".","4","1","9",".",".","5"],
     * [".",".",".",".","8",".",".","7","9"]
     * ]
     * 输出: true
     * 示例 2:
     * <p>
     * 输入:
     * [
     * ["8","3",".",".","7",".",".",".","."],
     * ["6",".",".","1","9","5",".",".","."],
     * [".","9","8",".",".",".",".","6","."],
     * ["8",".",".",".","6",".",".",".","3"],
     * ["4",".",".","8",".","3",".",".","1"],
     * ["7",".",".",".","2",".",".",".","6"],
     * [".","6",".",".",".",".","2","8","."],
     * [".",".",".","4","1","9",".",".","5"],
     * [".",".",".",".","8",".",".","7","9"]
     * ]
     * 输出: false
     * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
     * 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
     */
    public boolean isValidSudoku(char[][] board) {
        //使用3个Hashmap解决
//
        HashMap<Integer, Integer>[] rows = new HashMap[9];
//        HashMap<Integer, Integer>[] columns = new HashMap[9];
//        HashMap<Integer, Integer>[] boxes = new HashMap[9];
//        for (int i = 0; i < 9; i++) {
//            rows[i] = new HashMap<>();
//            columns[i] = new HashMap<>();
//            boxes[i] = new HashMap<>();
//        }
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                char num = board[i][j];
//                if (num != '.') {
//                    int n = num;
//                    int boxIndex = (i / 3) * 3 + j / 3;
//
//                    rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
//                    columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
//                    boxes[boxIndex].put(n, boxes[boxIndex].getOrDefault(n, 0) + 1);
//
//                    if (rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[boxIndex].get(n) > 1) {
//                        return false;
//                    }
//                }
//
//            }
//        }
        //优化后不使用Hashmap
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!isValid(board, i, j)) {
                    return false;
                }
            }
        }
        return true;

    }

    private boolean isValid(char[][] board, int row, int column) {
        char a = board[row][column];
        if (a == '.') {
            return true;
        }
        for (int i = 0; i < 9; i++) {
            if (i != row && board[i][column] == a) {
                return false;
            }
        }
        for (int j = 0; j < 9; j++) {
            if (j != column && board[row][j] == a) {
                return false;
            }
        }
        int boxRow = (row / 3) * 3;
        int boxColumn = (column / 3) * 3;

        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxColumn; j < boxColumn + 3; j++) {

                if (i != row && j != column && board[i][j] == a) {

                    return false;
                }
            }
        }
        return true;
    }
}

