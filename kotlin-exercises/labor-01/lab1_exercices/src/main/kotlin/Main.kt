import java.util.Base64

//3.
fun isPrime(a: Int): Boolean {
    if (a < 1) return false
    if (a != 2 && a % 2 == 0) return false
    for (n in 3..a / 2 step 2) {
        if (a % n == 0) return false
    }
    return true
}

//4.BASE64
fun encode(msg:String):String{
    return Base64.getEncoder().encodeToString(msg.toByteArray())
}
fun decode(msg:String)
        =String(Base64.getDecoder().decode(msg))

fun messageCoding(msg: String, func: (String) -> String): String {
    return func(msg)
}

////5.compact function
//fun evenNumber(a:Int):Boolean
//        = a%2==0

//5.compact function
fun evenNumber(numbers:List<Int>){
    val evenNumbers = numbers.filter{ it%2==0}
    evenNumbers.forEach{
        println(it)
    }
}

fun main() {
//1.
//	   println("Hello, world!!!")
//     val a="Hello"
//     var b:Int=2
    val sum = 2 + 3
    println("2+3=$sum")
//     println("sum=${2+3}")
    println()

//2.
    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    for (day in days)
        println(day)

    println("\nStarts with T:")
    days.filter {
        it.startsWith("T")
    }.forEach {
        println(it)
    }

    println("\nContains e:")
    days.filter {
        it.contains("e")
    }.forEach {
        println(it)
    }

    println("\nLength=6:")
    days.filter {
        it.length == 6
    }.forEach {
        println(it)
    }

// 3.
    println()
    println("Prime numbers between 1 and 10:")
    for (i in 1..10)
        if (isPrime(i))
            println(i)

//4.
    val message = "Hello, Maestro!"

    val encodedMessage = messageCoding(message, ::encode)
    val decodedMessage = messageCoding(encodedMessage, ::decode)

    println()
    println("Message: $message")
    println("Encoded message: $encodedMessage")
    println("Decoded message: $decodedMessage")


//5.
    val numbers=listOf(1, 3, 2, 5, 10, 12, 20, 55, 44, 26)
    println("\nEven numbers:")
    evenNumber(numbers)

//6. map
    println("\nDouble the elements of the list:")
    println(numbers.map{it*2})
    //vagy
//    numbers.map{it*2}.forEach{
//        println(it)
//    }
    println("\nDays capitalized:${days.map { it.uppercase() }}")

    println("\nFirst character for each day:")
    println(days.map { it[0] })

    println("\nLength of days:")
    println(days.map { it.length })

    var av:Double=0.0
    days.map { av=av+it.length }
    av=av/7
    println("\nAverage length of days: $av")

//7.
    days.toMutableList()
    // Remove days containing the letter 'n'
    val daysMutable = days.toMutableList()

    daysMutable.removeIf { day -> "n" in day }

    println("\nRemoved days that contained \"n\": $daysMutable")

    println()
    daysMutable.withIndex().forEach{
        println("Item at ${it.index} is ${it.value}")
    }

    println("\nDays sorted alphabetically:")
    daysMutable.sort()
    daysMutable.forEach{
        println(it)
    }

    //8.
    println("\n10 random integers between 0-100:")
    val arr=IntArray(10){(0..100).random()}
    arr.forEach {
        print("$it ")
    }

    println("\n\nArray sorted:")
    arr.sort()
    arr.forEach {
        print("$it ")
    }

    var even=0
    var alleven=1
    for (i in 0..9) {
        if(arr[i]%2==0){
            even=1
        }
        if(arr[i]%2!=0){
            alleven=0
        }
    }
    if(even==0){
        println("\nThe array doesen't contain even numbers")
    }
    else{
        println("\nThe array contain even numbers")
    }

    if(alleven==0){
        println("\nNot all numbers are even")
    }
    else{
        println("\nAll numbers are even")
    }

    var avg:Double=0.0
    arr.forEach {
        avg+=it
    }
    avg/=10
    println("\nAverage of generated numbers: $avg")
}