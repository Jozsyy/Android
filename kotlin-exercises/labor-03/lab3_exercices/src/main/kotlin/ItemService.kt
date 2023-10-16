class ItemService (private val itemRepository: ItemRepository){

    fun selectRandomItems(numberOfItems: Int): List<Item>{
        if(numberOfItems > itemRepository.size()) {
            println("Nincs ennyi kerdes")
            return emptyList()
        }

        val items:MutableList<Item> = mutableListOf<Item>()

        while(items.size < numberOfItems){
            val item:Item = itemRepository.randomItem()
            if(!items.contains(item)){
                items.add(item)
            }
        }
        return items
    }
}