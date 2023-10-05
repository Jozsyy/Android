import java.io.File
import java.util.TreeSet

class TreeSetDictionary : IDictionary {
    private val words =TreeSet<String>()

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