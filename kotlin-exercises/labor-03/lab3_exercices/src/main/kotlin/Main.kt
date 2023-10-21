fun main(args: Array<String>) {
    println("\t!!!QUIZ!!!")
    println("Valaszkent a sorszamot add meg!\n")

    //val itemController = ItemController(ItemService(ItemRepository))  //Egyszeru meghivas
    val repo = ItemRepository
    val service = ItemService(repo)
    val itemController =  ItemController(service)

    itemController.quiz(6)
}