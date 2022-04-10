import 'category.dart';

class MenuItemModel {
  int? id;
  String? imgUrl;
  String? name;
  String? description;
  String? color;
  num? price;
  CategoryModel? category;
  int? categoryId;

  MenuItemModel({
    this.id,
    this.imgUrl,
    this.name,
    this.description,
    this.color,
    this.price,
    this.category,
    this.categoryId
  });

  static List<MenuItemModel> generateMenuItems() {
    return [
      MenuItemModel(
        id: 1,
        imgUrl: 'assets/images/cheeseburger.jpeg',
        name: 'Cheeseburger',
        description: 'Hamburger + cheese, pomodoro e lattuga',
        color: 'red',
        price: 8.50,
        categoryId: 1
      ),
      MenuItemModel(
        id: 2,
        imgUrl: 'assets/images/baconburger.jpeg',
        name: 'Bacon burger',
        description: 'Hamburger + cheese, bacon',
        color: 'blue',
        price: 8.50,
        categoryId: 1
      ),
      MenuItemModel(
        id: 3,
        imgUrl: 'assets/images/hamburger1.jpeg',
        name: 'Seller 1',
        description: 'Hamburger + cheese, verdure grigliate',
        color: 'orange',
        price: 10,
        categoryId: 1
      ),
      MenuItemModel(
        id: 4,
        imgUrl: 'assets/images/hamburger4.jpg',
        name: 'Seller 4',
        description: 'Doppio Hamburger + cheese, verdure grigliate',
        color: 'yellow',
        price: 13,
        categoryId: 1
      ),
      MenuItemModel(
        id: 5,
        imgUrl: 'assets/images/hamburger4.jpg',
        name: 'Seller 4',
        description: 'Doppio Hamburger + cheese, verdure grigliate',
        color: 'yellow',
        price: 13,
        categoryId: 1
      ),
      MenuItemModel(
        id: 6,
        imgUrl: 'assets/images/hamburger4.jpg',
        name: 'Seller 4',
        description: 'Doppio Hamburger + cheese, verdure grigliate',
        color: 'yellow',
        price: 13,
        categoryId: 1
      ),
    ];
  }
}
