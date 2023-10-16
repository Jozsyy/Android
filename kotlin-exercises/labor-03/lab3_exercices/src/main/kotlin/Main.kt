fun main(args: Array<String>) {

    val repo=ItemRepository
    val item=repo.randomItem()
    println("Random item = $item")
//    val repo = ItemRepository
//    val service = ItemService(repo)
//    val itemController =  ItemController(service)

    //val itemController = ItemController(ItemService(ItemRepository))  //vagy igy

    itemController.quiz(3)
}