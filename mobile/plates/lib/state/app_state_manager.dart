import 'package:flutter/material.dart';

class AppTabs {
  static const int explore = 0;
  static const int plates = 1;
  static const int config = 2;
}

class AppStateManager extends ChangeNotifier {

  int _selectedTab = AppTabs.plates;

  int get getSelectedTab => _selectedTab;

  void goToTab(index) {
    _selectedTab = index;
    notifyListeners();
  }

}