interface IDictionary {
    fun add(word:String):Boolean
    fun find(word:String):Boolean
    fun size():Int

    companion object{
        const val DICTIONARY_NAME = "C:\\University\\Android\\kotlin-exercises\\labor-02\\lab2_exercices\\src\\main\\resources\\dict.txt"
    }
}
