/*
package jpwp.jpwpproject.dbInit;


import jpwp.jpwpproject.model.Translation;
import jpwp.jpwpproject.model.Word;
import jpwp.jpwpproject.service.TranslationService;
import jpwp.jpwpproject.service.WordService;
import jpwp.jpwpproject.util.Language;
import jpwp.jpwpproject.util.Level;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class Init {
    private final WordService wordService;
    private final TranslationService translationService;

    public Init(WordService wordService, TranslationService translationService) {
        this.wordService = wordService;
        this.translationService = translationService;
    }

    // @EventListener(ApplicationReadyEvent.class)
    public void start() throws IOException, InvalidFormatException {

        XSSFWorkbook wb = new XSSFWorkbook(new File("/home/michal/IdeaProjects/JPWP-project/src/main/java/jpwp/jpwpproject/dbInit/Zeszyt1.xlsx"));
        XSSFSheet sheet = wb.getSheetAt(0);
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            String plVocab = row.getCell(0).toString();
            if (plVocab.isEmpty()) {
                continue;
            }
            String enVocab = row.getCell(1).toString();
            String frVocab = row.getCell(2).toString();
            String esVocab = row.getCell(3).toString();
            String deVocab = row.getCell(4).toString();

            String enDefinition = row.getCell(5).toString();
            String frDefinition = row.getCell(6).toString();
            String esDefinition = row.getCell(7).toString();
            String deDefinition = row.getCell(8).toString();

            String plGramCategory = row.getCell(9).toString();
            String enGramCategory = row.getCell(10).toString();
            String frGramCategory = row.getCell(11).toString();
            String esGramCategory = row.getCell(12).toString();
            String deGramCategory = row.getCell(13).toString();

            String plCollocations = row.getCell(14).toString();
            String enCollocations = row.getCell(15).toString();
            String frCollocations = row.getCell(16).toString();
            String esCollocations = row.getCell(17).toString();
            String deCollocations = row.getCell(18).toString();

            String plTopic = row.getCell(19).toString();
            String enTopic = row.getCell(20).toString();
            String frTopic = row.getCell(21).toString();
            String esTopic = row.getCell(22).toString();
            String deTopic = row.getCell(23).toString();

            String levelString = row.getCell(24).toString();
            Level level = null;
            if (levelString.contains("A1")) {
                level = Level.A1;
            } else if (levelString.contains("A2")) {
                level = Level.A2;
            } else if (levelString.contains("B1")) {
                level = Level.B1;
            } else if (levelString.contains("B2")) {
                level = Level.B2;
            } else if (levelString.contains("C1")) {
                level = Level.C1;
            } else if (levelString.contains("C2")) {
                level = Level.C2;
            }
      */
/*      if (plVocab.contains(";")) {
                String[] plVocabSplit = plVocab.split(";");
                for (int j = 0; j < plVocabSplit.length; j++) {
                    save(plVocabSplit[j], plGramCategory, plCollocations, plTopic, enVocab, enGramCategory, enDefinition, enCollocations, enTopic, level);
                }*//*

            */
/*     } else {*//*

            save(plVocab, plGramCategory, plCollocations, plTopic, enVocab, enGramCategory, enDefinition, enCollocations, enTopic, level);
            // }
        }
    }

    public void save(String plVocab, String plGramCategory, String plCollocations, String plTopic, String enVocab, String enGramCategory, String enDefinition, String enCollocations, String enTopic, Level level) {

        Word plWord = new Word(plVocab, plGramCategory, plCollocations, plTopic);
        Translation enTranslation = new Translation(Language.English, enVocab, enGramCategory, enDefinition, enCollocations, enTopic, level, plWord);
         */
/*   Translation frTranslation = new Translation(Language.French, frVocab, frGramCategory, frDefinition, frCollocations, frTopic, level, plWord);
            Translation esTranslation = new Translation(Language.Spanish, esVocab, esGramCategory, esDefinition, esCollocations, esTopic, level, plWord);
            Translation deTranslation = new Translation(Language.German, deVocab, deGramCategory, deDefinition, deCollocations, deTopic, level, plWord);*//*


        ArrayList<Translation> translations = new ArrayList();
        translations.add(enTranslation);
    */
/*        translations.add(frTranslation);
            translations.add(esTranslation);
            translations.add(deTranslation);*//*


        plWord.setTranslation(translations);

        wordService.save(plWord);
        translationService.save(enTranslation);
*/
/*            translationService.save(frTranslation);
            translationService.save(esTranslation);
            translationService.save(deTranslation);*//*


    }
}


*/
