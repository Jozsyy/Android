import java.io.File

//Javaban implements = Kotlin :
//ha object akkor szingleton
class ListDictionary : IDictionary {
    private  val words = mutableListOf<String>()

    //konstruktor
    init {
        File(IDictionary.DICTIONARY_NAME).forEachLine {
            words.add(it)
        }
    }

    override fun add(word: String): Boolean {
        return words.add(word)
    }

    override fun find(word: String) = words.find { it == word } != null


    override fun size() = words.size

}