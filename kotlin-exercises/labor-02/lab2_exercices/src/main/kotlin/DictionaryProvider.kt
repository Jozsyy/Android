class DictionaryProvider {

    companion object{
        fun createDictionary(type: DictionaryType):IDictionary{
            return when (type){
                DictionaryType.ARRAY_LIST -> ListDictionary()
                DictionaryType.HASH_SET -> HashSetDictionary    //ha szingleton akkor nincs konstruktorhivas
                DictionaryType.TREE_SET -> TreeSetDictionary()
            }
        }
    }
}