class ItemController(private val itemService:ItemService) {

    fun quiz(numberOfQuestions : Int) {
        val questions=itemService.selectRandomItems(numberOfQuestions)

        var i:Int=0
        var ans:String?
        var correct:Int=0
        while(i<numberOfQuestions){
            println("${i+1}. Kerdes: ${questions[i].question}")  //print the qustion
            println("1.${questions[i].answer[0]}\n2.${questions[i].answer[1]}\n3.${questions[i].answer[2]}\n4.${questions[i].answer[3]}")    //print answers

            ans= readLine() //reading answer from console
            if (ans != null) {
                if(ans.toInt()==questions[i].correct){
                    correct++
                }
            }
            ++i
        }
        println("A quiz veget ert! Helyes valaszok: $correct/$numberOfQuestions")  //print the result
    }

}