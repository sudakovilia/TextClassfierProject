package ru.caf82.result.machinelearning.preprocessing;

import java.util.*;

/**
 * Created by ilysko on 03.08.17.
 */
public class CountVectorizer implements SequenceProcessor, Transformer {
    /**
     * Convert text to matrix of word counts
     */

    /**
     * @param minDf -- float in range [0.0, 1.0]. Will ignore terms that have a document frequency strictly higher than the given threshold. Default: 1.0
     * @param maxDf -- float in range [0.0, 1.0]. Will ignore terms that have a document frequency strictly lower than the given threshold. Default: 0.0
     * @param stopWords -- a built-in stop word list for Russian.
     * @param defaultStopWordsList -- default stop word list.
     * @param parralize -- work with texts in parallel mode.
     */

    private Map<String, Integer> matrix = new HashMap<>();
    private List<String> stopWords = new ArrayList<>();
    final float minDf;
    final float maxDf;
    final boolean parralize;
    private static List<String> defaultStopWordsList = new ArrayList<String>(
            Arrays.asList(new String[]{
                    "а","е","и","ж","м","о","на","не","ни","об","но","он","мне","мои","мож","она","они","оно",
                    "мной","много","многочисленное","многочисленная","многочисленные","многочисленный",
                    "мною","мой","мог","могут","можно","может","можно","мор","моя","моё","мочь","над","нее",
                    "оба","нам","нем","нами","ними","мимо","немного","одной","одного","менее","однажды","однако",
                    "меня","нему","меньше","ней","наверху","него","ниже","мало","надо","один","одиннадцать","одиннадцатый",
                    "назад","наиболее","недавно","миллионов","недалеко","между","низко","меля","нельзя","нибудь",
                    "непрерывно","наконец","никогда","никуда","нас","наш","нет","нею","неё","них","мира","наша",
                    "наше","наши","ничего","начала","нередко","несколько","обычно","опять","около","мы","ну","нх","от","отовсюду",
                    "особенно","нужно","очень","отсюда","в","во","вон","вниз","внизу","вокруг","вот","восемнадцать",
                    "восемнадцатый","восемь","восьмой","вверх","вам","вами","важное","важная","важные","важный","вдали","везде",
                    "ведь","вас","ваш","ваша","ваше","ваши","впрочем","весь","вдруг","вы","все","второй","всем","всеми","времени","время",
                    "всему","всего","всегда","всех","всею","всю","вся","всё","всюду","г","год","говорил","говорит","года","году","где",
                    "да","ее","за","из","ли","же","им","до","по","ими","под","иногда","довольно","именно","долго","позже",
                    "более","должно","пожалуйста","значит","иметь","больше","пока","ему","имя","пор","пора","потом","потому","после",
                    "почему","почти","посреди","ей","два","две","двенадцать","двенадцатый","двадцать","двадцатый",
                    "двух","его","дел","или","без","день","занят","занята","занято","заняты","действительно","давно",
                    "девятнадцать","девятнадцатый","девять","девятый","даже","алло","жизнь","далеко","близко","здесь","дальше","для",
                    "лет","зато","даром","первый","перед","затем","зачем","лишь","десять","десятый","ею","её","их","бы","еще","при",
                    "был","про","процентов","против","просто","бывает","бывь","если","люди","была","были","было","будем","будет","будете","будешь",
                    "прекрасно","буду","будь","будто","будут","ещё","пятнадцать","пятнадцатый","друго","другое","другой","другие","другая","других","есть","пять",
                    "быть","лучше","пятый","к","ком","конечно","кому","кого","когда","которой","которого","которая","которые","который","которых","кем",
                    "каждое","каждая","каждые","каждый","кажется","как","какой","какая","кто","кроме","куда","кругом","с","т","у","я",
                    "та","те","уж","со","то","том","снова","тому","совсем","того","тогда","тоже","собой","тобой","собою","тобою",
                    "сначала","только","уметь","тот","тою","хорошо","хотеть","хочешь","хоть","хотя","свое","свои","твой","своей","своего","своих",
                    "свою","твоя","твоё","раз","уже","сам","там","тем","чем","сама","сами","теми","само","рано","самом","самому",
                    "самой","самого","семнадцать","семнадцатый","самим","самими","самих","саму",
                    "семь","чему","раньше","сейчас","чего","сегодня","себе","тебе","сеаой","человек","разве","теперь","себя","тебя","седьмой","спасибо",
                    "слишком","так","такое","такой","такие","также","такая","сих","тех","чаще","четвертый","через","часто","шестой","шестнадцать","шестнадцатый",
                    "шесть","четыре","четырнадцать","четырнадцатый","сколько","сказал","сказала","сказать",
                    "ту","ты","три","эта","эти","что","это","чтоб","этом","этому","этой","этого","чтобы","этот","стал","туда",
                    "этим","этими","рядом","тринадцать","тринадцатый","этих","третий","тут","эту","суть","чуть","тысяч"
            })
    );

    public CountVectorizer(float minDf, float maxDf, List<String> stopWords, boolean parralize) {
        this.maxDf = minDf; // TODO work with minDf parameter
        this.minDf = maxDf; // TODO work with maxDf parameter
        this.stopWords = stopWords;
        this.parralize = parralize; // TODO do parallel mode
    }

    public CountVectorizer(float minDf, float maxDf) {
        this(minDf, maxDf, defaultStopWordsList, false);
    }

    public CountVectorizer(List<String> stopWords) {
        this(0.0F, 1.0F, stopWords, false);
    }

    public CountVectorizer(boolean parralize) {
        this(0.0F, 1.0F, defaultStopWordsList, parralize);
    }

    public CountVectorizer() {
        this(0.0F, 1.0F, defaultStopWordsList, false);
    }

    public float getMinDf() {
        return minDf;
    }

    public float getMaxDf() {
        return  maxDf;
    }

    public boolean getParralize() {
        return parralize;
    }

    public Map<String, Integer> getMatrix() {
        return matrix;
    }

    /**
     * This function make text "cleaning" and create list of words.
     * @param text -- String with text.
     * @return list of words from text.
     */
    @Override
    public List<String> preprocess(String text) {
        String modifiedText = new String(text);

        modifiedText = modifiedText.toLowerCase();
        modifiedText = modifiedText.replaceAll(" - ?", "-");
        modifiedText = modifiedText.replaceAll("[^а-я -]","");
        modifiedText = modifiedText.replaceAll("- "," ");
        modifiedText = modifiedText.replaceAll("  *"," ");

        List<String> wordsList = new ArrayList<>(Arrays.asList(modifiedText.split(" ")));

        wordsList.removeAll(stopWords);

        return wordsList;
    }

    /**
     * Get list of words that represent text and return map with word frequency,
     * WITHOUT changing matrix field.
     * @param words -- list of words (String) from one text.
     * @return word frequency in map
     */
    private Map<String, Integer> countWords(List<String> words) {
        Set<String> keys = new HashSet<String>(words);
        Map<String, Integer> resultTextCount = new HashMap<>();
        for (String word: keys) {
            resultTextCount.put(word, Collections.frequency(words, word));
        }

        return resultTextCount;
    }

    /**
     * Get list of words that represent text and return map with word frequency,
     * WITH changing matrix field.
     * @param words -- list of words (strings) from one text.
     * @return word frequency in map
     */
    private Map<String, Integer> countWordsAndEditMatrix(List<String> words) {
        Set<String> keys = new HashSet<String>(words);
        Map<String, Integer> resultTextCount = new HashMap<>();
        for (String word: keys) {
            resultTextCount.put(word, Collections.frequency(words, word));
            matrix.put(word, (matrix.containsKey(word)) ? matrix.get(word) +
                    Collections.frequency(words, word) : Collections.frequency(words, word));
        }

        return resultTextCount;
    }

    /**
     * Learn matrix by parsing all texts text.
     * @param listOfTexts -- list of texts in String
     */
    @Override
    public void fit(List<String> listOfTexts) {
        for (String text: listOfTexts) {
            List<String> words = preprocess(text);
            countWordsAndEditMatrix(words);
        }
    }

    /**
     * Learn matrix by parsing all texts text and return list with word frequency.
     * @param listOfTexts -- list of texts in String
     * @return list of maps with word frequency
     */
    @Override
    public List<Map<String, Integer>> fitAndTransform(List<String> listOfTexts) {
        List<Map<String, Integer>> resultForAllTextsCount =
                new ArrayList<>();
        for (String text: listOfTexts) {
            List<String> words = preprocess(text);
            resultForAllTextsCount.add(countWordsAndEditMatrix(words));
        }
        return resultForAllTextsCount;
    }

    /**
     * Return list with word frequency.
     * @param listOfTexts -- list of texts in String
     * @return list of maps with word frequency
     */
    @Override
    public List<Map<String, Integer>> transform(List<String> listOfTexts) {
        List<Map<String, Integer>> resultForAllTextsCount =
                new ArrayList<>();
        for (String text: listOfTexts) {
            List<String> words = preprocess(text);
            resultForAllTextsCount.add(countWords(words));
        }
        return resultForAllTextsCount;
    }

    /**
     * Test for this class
     */
    public static void main(String[] args) {

        CountVectorizer countVectorizer = new CountVectorizer();

        System.out.println("Initial matrix:\n" + countVectorizer.getMatrix() + "\n");
        countVectorizer.fit(Arrays.asList(new String[]{"Онлайн-курс является упрощенной и укороченной версией курса ОС, читаемого в Академическом университете. " +
                "Но несмотря на небольшую продолжительность курс довольно детально покрывает следующие темы:\n" +
                "управление памятью (разделение на физическую и логическую память, аппаратные средства организации логической памяти, алгоритмы аллокации)\n" +
                "планирование и многозадачность (вытесняющая и невытесняющая многозадачность, критерии планирования, переключение потоков управления)\n" +
                "средства синхронизации потоков (взаимное исключение, реализация примитивов взаимного исключения, дедлоки и средства борьбы с ними)\n" +
                "пространство пользователя и системные вызовы (реализации системных вызовов, структура исполняемого файла, запуск приложений)",

                "Предполагается, что информации, данной в курсе, достаточно для написания простейшей многозадачной ОС, но так как проверять подобные задания" +
                "в полностью автоматическим режиме довольно тяжело, то мы не будем просить вас это делать. Вместо этого по мере прохождения мы просто будем" +
                "предоставлять вам доступ к исходным кодам, демонстрирующим информацию из курса на примере простейшей учебной ОС. Для того чтобы работать с примерами," +
                "вам понадобятся средства разработки GNU (компилятор языка С и компоновщик) и утилита make."}));

        System.out.println("Matrix after fit function:\n" + countVectorizer.getMatrix() + "\n");

        List<Map<String, Integer>> res = countVectorizer.fitAndTransform(Arrays.asList(new String[] {"Отличный курс. Понравился формат" +
                "подачи материала - все коротко и по делу. Короткие ролики не дают устать и позволяют воспринимать материал" +
                "дозированно. Понравился жизнерадостный автор) Пожелания. 1 Сделайте главу, рассказывающую об особенностях" +
                "популярных ОС. Хотя бы без задач, просто обзорно. 2 Перед тем, как давать задачи на ассемблере, приводилось" +
                "очень подробное описание особенностей языка. Однако потом пошли задачи на С, и там уже таких пояснений по С" +
                "не было. С учетом того, что это не самый простой язык, хотелось бы, чтобы такие видео с описанием примеров решения" +
                "задач были и для С. В целом хочется поблагодарить автора курса за возможность получать знания."}));

        System.out.println("Matrix after fitAndTransform function:\n" + countVectorizer.getMatrix());
        System.out.println("Result matrix after fitAndTransform function:\n" + res + "\n");

        List<Map<String, Integer>> anotherRes = countVectorizer.transform(Arrays.asList(new String[] {"Отличный курс. Понравился формат" +
                "подачи материала - все коротко и по делу. Короткие ролики не дают устать и позволяют воспринимать материал" +
                "дозированно. Понравился жизнерадостный автор) Пожелания. 1 Сделайте главу, рассказывающую об особенностях" +
                "популярных ОС. Хотя бы без задач, просто обзорно. 2 Перед тем, как давать задачи на ассемблере, приводилось" +
                "очень подробное описание особенностей языка. Однако потом пошли задачи на С, и там уже таких пояснений по С" +
                "не было. С учетом того, что это не самый простой язык, хотелось бы, чтобы такие видео с описанием примеров решения" +
                "задач были и для С. В целом хочется поблагодарить автора курса за возможность получать знания."}));

        System.out.println("Matrix after transform function:\n" + countVectorizer.getMatrix());
        System.out.println("Result matrix after transform function:\n" + anotherRes);
    }
}
