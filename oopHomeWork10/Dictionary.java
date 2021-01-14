package oopHomeWork10;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Dictionary {
    private final static String DICTIONARY_FILE = "src\\oopHomeWork10\\EnUaTranslate.dic";
    private final static String UKR_FILE = "src\\oopHomeWork10\\Ukrainian.out";
    private final static String ENG_FILE = "src\\oopHomeWork10\\English.in";
    private Set<Character> punctuation;
    private Map<String, String> dictionary;

    public Dictionary() {
        punctuation = new HashSet<>(){{
            add('.');
            add(',');
            add(';');
            add('!');
            add('?');
        }};
    }

    public void translate(){
        loadDict();
        String engWords[] = readEngFromFile();
        Stream<String> ukrWordsStream = Arrays.stream(engWords).map(this::internalTranslate);
        saveUkrToFile(ukrWordsStream);
        System.out.printf("Translated from %s to %s\n", ENG_FILE, UKR_FILE);
    }

    private String internalTranslate(String engWord) {
        engWord = engWord.toLowerCase();
        char lastChar = engWord.charAt(engWord.length() - 1);
        if (punctuation.contains(lastChar)) {
            String engW = engWord.substring(0, engWord.length() - 1);
            String ukrWord = dictionary.getOrDefault(engW, engW);
            return ukrWord + lastChar;
        } else
            return dictionary.getOrDefault(engWord, engWord);
    }
    
    private void saveUkrToFile(Stream<String> ukrWords) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(UKR_FILE))) {
           ukrWords.forEach(ukrWord -> {
               try {
                   bf.write(ukrWord + " ");
               } catch (IOException e) {
                   e.printStackTrace();
               }
           });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inputDict(){
        loadDict();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("For exit type exit");
            while(true) {
                System.out.println("Enter english word:");
                String engWord = sc.nextLine();
                if ("exit".equals(engWord))
                    break;
                System.out.println("Enter ukrainian translate:");
                String ukrWord = sc.nextLine();
                if ("exit".equals(ukrWord))
                    break;
                dictionary.put(engWord.toLowerCase(), ukrWord.toLowerCase());
            }
        }
        saveDictToFile();
    }

    private String[] readEngFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(ENG_FILE))){
            String text = "";
            String line;
            while ((line = br.readLine()) != null) {
                text += line;
            }
            return text.split(" ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadDict() {
        if (dictionary != null)
            return;
        try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(DICTIONARY_FILE))){
            dictionary =  (Map)OIS.readObject();
        } catch (IOException e) {
            System.out.println("Error reading file EnUaTranslate.dic. Dictionary is empty");
            dictionary = new HashMap<>();
        } catch (ClassNotFoundException e) {
            System.out.println("Dictionary is empty");
            dictionary = new HashMap<>();
        }
    }

    private void saveDictToFile(){
        try(ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(DICTIONARY_FILE))){
            OOS.writeObject(dictionary);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
