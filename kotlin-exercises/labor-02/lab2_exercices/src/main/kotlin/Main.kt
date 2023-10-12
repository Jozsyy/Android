import java.util.Random


//2. Extension function - letezo osztalyt kibovit valamivel anelkul, hogy ujat szarmaztatnank
fun String.nameMonogram():String = this.split(" ").map{ it[0] }.joinToString("-")

fun List<String>.joinedList(separator:String) = this.joinToString(separator)

fun List<String>.longestElement() = this.maxByOrNull { it.length }

fun Date.isLeap():Boolean = this.year % 4 ==0

fun Date.isValid() :Boolean {
    if(this.year<0 || this.year>2023) return false
    if(this.month<1 || this.month>12) return false
    if(this.day<1 || this.day>31) return false

    //February
    if(this.month==2){
        if(this.isLeap()){
            return this.day<=29   //leap year
        }
        else{
            return this.day<=28
        }
    }

    if(this.month==2 || this.month==4 || this.month==6 || this.month==9 || this.month==11){
        return this.day<=30
    }
    return true
}

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



//   //Problem 2.
//    //Monogram
//    val name:String="John Smith"
//    println(name.nameMonogram())
//
//    //byseparator
//    val list:List<String> = listOf("apple","pear","melon")
//    val separator:String = "#"
//    println(list.joinedList(separator))
//
//    //longest element
//    val list2:List<String> = listOf("apple","pear","strawberry","melon")
//    println(list2.longestElement())

    //Problem 3.
    var i=0
    val dates = mutableListOf<Date>()
    while(i<10){
        val random = Random()
        val year = 1900 + random.nextInt(200)
        val month = 3 + random.nextInt(12)
        val day = 10 + random.nextInt(25)
        val date:Date = Date(year,month,day)
        if(date.isValid()){
            ++i;
            dates.add(date);
        }
        else{
            println("Not valid date: ${date.toString()}")
        }
    }
    dates.forEach {
        println("Valid date: ${it.toString()}")
    }

    //sort by natural ordering
    dates.sort()
    println("Sorted list:")
    dates.forEach {
        println(it.toString())
    }

    dates.sortDescending()
    println("Sorted reverse:")
    dates.forEach {
        println(it.toString())
    }

    //Sort by using a custom ordering
    dates.sortWith(Date.customDateComparator);
    println("Sorted with custom oreding:")
    dates.forEach {
        println(it.toString())
    }
}

