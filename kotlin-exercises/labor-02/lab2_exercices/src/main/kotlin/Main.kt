fun main(){
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

    //masodik fele
    val dict:IDictionary=DictionaryProvider.createDictionary(ListDictionary)

    //2.
    val name:String="John Smith"
    println(name.nameMonogram())
    //byseparator
    //longest element
}

//2. Extension function - letezo osztalyt kibovit valamivel anelkul, hogy ujat szarmaztatnank
fun String.nameMonogram():String = this.split(" ").map{ it[0] }.joinToString("-")
