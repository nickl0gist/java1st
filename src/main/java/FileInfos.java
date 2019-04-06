import dnl.utils.text.table.TextTable;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.*;

public class FileInfos {


    public static void main(String[] args) throws IOException {
        String directory = "E:\\UBU\\testFiles\\";
        Path path = Paths.get(directory);
        final List<Path> files = new ArrayList<>();


        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    continue;
                }
                files.add(entry);
            }
        }
        Object[][] data = new Object[files.size()][3];
        for (int i = 0; i < files.size(); i++) {
            data[i][0] = files.get(i).getFileName();
            data[i][1] = String.format("%.2f",FileChannel.open(files.get(i)).size() / 1024.0) + " Kb" ;
            data[i][2] = files.get(i).getFileName().toString().substring(files.get(i).getFileName().toString().lastIndexOf("."));
        }

        String[] columnNames = {
                "File Name",
                "Size",
                "file extension"};

        TextTable tt = new TextTable(columnNames, data);
        tt.setAddRowNumbering(true);
        tt.printTable();
    }

}

