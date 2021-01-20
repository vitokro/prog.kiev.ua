package pro_hw2;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.Consumer;

@Save(id = 5)
public class SerialSave {
    private static final String FILE_SERIAL = "src/pro_hw2/serial.txt";
    @Save
    private int elders;
    @Save
    private long crossroads;
    @Save
    private String blackBall;
    private String whiteBall;
    @Save
    private String redBall;
    @Save
    private char joe;
    @Save
    private int jennifer;

    public static void main(String[] args) {
//        3**. Написать код, который сериализирует и десериализирует в/из файла все значения полей
//класса, которые отмечены аннотацией @Save.
        try {
            serial(12, 343346L, "black", "white", "red", 'Ї', 0);
            SerialSave serialSave = deSerial();
            System.out.println(serialSave);
        } catch (IOException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

    }


    public SerialSave() {
    }

    public SerialSave(int elders, long crossroads, String blackBall, String whiteBall, String redBall, char joe, int jennifer) {
        this.elders = elders;
        this.crossroads = crossroads;
        this.blackBall = blackBall;
        this.whiteBall = whiteBall;
        this.redBall = redBall;
        this.joe = joe;
        this.jennifer = jennifer;
    }

    public static void serial(int i, long l, String black, String white, String red, char c, int i1) throws IOException {
        Class<?> cls = SerialSave.class;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_SERIAL)) ) {
            Field[] declaredFields = cls.getDeclaredFields();
            int fieldsNumber = (int)Arrays.stream(declaredFields)
                    .filter(field -> field.isAnnotationPresent(Save.class))
                    .count();
            bw.write(fieldsNumber + "\n");
            Arrays.stream(declaredFields)
                    .filter(field -> field.isAnnotationPresent(Save.class))
                    .forEach(internalSerial(cls, bw, i, l, black, white, red, c, i1));
        }
    }

    @NotNull
    private static Consumer<Field> internalSerial(Class<?> cls, BufferedWriter bw, int i, long l, String black, String white, String red, char c, int i1) throws IOException {
        Save save = cls.getAnnotation(Save.class);
        bw.write(save.id() + "\n");
        return field -> {
            field.setAccessible(true);
            try {
                Constructor<?> con = cls.getConstructor(int.class,
                        long.class,
                        String.class,
                        String.class,
                        String.class,
                        char.class,
                        int.class);
                SerialSave serialSave = (SerialSave) con.newInstance(i, l, black, white, red, c, i1);
                bw.write(field.getName() + "=" +field.get(serialSave) + "\n");

            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | IOException e) {
                e.printStackTrace();
            }

        };
    }
    @NotNull
    private static SerialSave deSerial() throws IOException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {
        SerialSave serialSave;
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_SERIAL))){
            int fieldsNumber = Integer.valueOf(br.readLine());
            Class<?> cls = SerialSave.class;
            serialSave = (SerialSave) cls.getConstructor().newInstance();
            Save save = cls.getAnnotation(Save.class);
            long saveId = Long.parseLong(br.readLine());
            if (saveId != save.id())
                throw new SerialSaveException("Id from file is not valid! Should be " + save.id() + " but found " + saveId);

            for (int i = 0; i < fieldsNumber; i++) {
                String[] split = br.readLine().split("=");
                Field field = cls.getDeclaredField(split[0]);
                field.setAccessible(true);
                setFieldValue(serialSave, split[1], field);
            }
        }
        return serialSave;

    }
    // we do not know the type of field exactly, hence forced to iterate all types
    private static void setFieldValue(SerialSave serialSave, String value, Field field) throws IllegalAccessException {
        if (field.getType().equals(int.class))
            field.set(serialSave, Integer.parseInt(value));
        else if (field.getType().equals(String.class))
            field.set(serialSave, value);
        else if (field.getType().equals(long.class))
            field.set(serialSave, Long.parseLong(value));
        else if (field.getType().equals(char.class))
            field.set(serialSave, value.charAt(0));
    }

    @Override
    public String toString() {
        return "SerialSave{" +
                "elders=" + elders +
                ", crossroads=" + crossroads +
                ", blackBall='" + blackBall + '\'' +
                ", whiteBall='" + whiteBall + '\'' +
                ", redBall='" + redBall + '\'' +
                ", joe=" + joe +
                ", jennifer=" + jennifer +
                '}';
    }
}
