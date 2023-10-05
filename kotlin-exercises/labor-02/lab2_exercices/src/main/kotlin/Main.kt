

//2. Extension function - letezo osztalyt kibovit valamivel anelkul, hogy ujat szarmaztatnank
fun String.nameMonogram():String = this.split(" ").map{ it[0] }.joinToString("-")

fun List<String>.joinedList(separator:String) = this.joinToString(separator)

fun List<String>.longestElement() = this.maxByOrNull { it.length }

fun main(){

    //Problem 1.
//    val dict: IDictionary = ListDictionary()
//    println("Number of words: ${dict.size()}")
//    var word: String?
//    while(true){
//        print("What to find? ")
//        word = readLine()
//        if( word.equals("quit")){
//            break
//        }
//        println("Result: ${word?.let { dict.find(it) }}")
//    }


    //TreeSet, HashSet
//    val dict:IDictionary=DictionaryProvider.createDictionary(DictionaryType.HASH_SET) //DictionaryType.TREE_SET
//    println("Number of words: ${dict.size()}")
//    var word: String?
//    while(true){
//        print("What to find? ")
//        word = readLine()
//        if( word.equals("quit")){
//            break
//        }
//        println("Result: ${word?.let { dict.find(it) }}")
//    }



   //Problem 2.
    //Monogram
    val name:String="John Smith"
    println(name.nameMonogram())

    //byseparator
    val list:List<String> = listOf("apple","pear","melon")
    val separator:String = "#"
    println(list.joinedList(separator))

    //longest element
    val list2:List<String> = listOf("apple","pear","strawberry","melon")
    println(list2.longestElement())
}