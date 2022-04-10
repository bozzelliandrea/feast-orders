import 'package:orders/categories/models/menu_item.dart';

class CategoryModel {
  int? id;
  String? imgUrl;
  String? name;
  String? description;
  String? color;
  List<MenuItemModel>? menuItemList;

  CategoryModel({ this.id, this.imgUrl, this.name, this.description, this.color, this.menuItemList });

  static List<CategoryModel> generateCategories() {
    return [
      CategoryModel(
        id: 1,
        imgUrl: '',
        name: 'Hamburger',
        description: 'Hamburger',
        color: 'red',
        menuItemList: MenuItemModel.generateMenuItems()
      )
    ];
  }

}
