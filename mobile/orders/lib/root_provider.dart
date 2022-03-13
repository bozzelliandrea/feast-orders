import 'package:commons/commons.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:bloc/bloc.dart';
import 'package:orders/repositories/repository_bundle.dart';

import 'configuration.dart';

/// Provides a set of [Bloc]s and the [RepositoryBundle] globally from the root of the app
@immutable
class RootProvider extends StatelessWidget {

  final Widget child;
  final ApiService apiService;
  final AuthSessionStorage authSessionStorage;

  const RootProvider({
    Key? key,
    required this.child,
    required this.apiService,
    required this.authSessionStorage
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return RepositoryProvider<RepositoryBundle>(
      lazy: false,
      create: (context) => RepositoryBundle(
        mock: Configuration.useMockData,
        apiService: apiService,
        authSessionStorage: authSessionStorage
      ),
      child: child,
      // child: MultiBlocProvider(
      //   providers: _buildBlocProvider(),
      //   child: child,
      // ),
    );
  }
}

///Builds the global [BlocProvider]s
///
///We need to provide these globally because:
///The [CurrentOrderBloc] is needed by the [Header] to display the current amount
///of [Dish]es in the order, by the [OrderPage] to display the [Dish]es in the
///order & by the [MenuPage] to add new [Dish]es to the order.
///
///The [BookingBloc] is needed by the [BookingPage] to place new bookings &
///by the [OrderPage] to auto fill-in the booking token.
///
///The [LocalizationBloc] is needed by the [Header] to set a new [Locale] &
///by the [MaterialApp] to update the current [Locale].
// List<BlocProvider<Bloc<dynamic, dynamic>>> _buildBlocProvider() => [
//   BlocProvider<CurrentOrderBloc>(
//     builder: (BuildContext context) => CurrentOrderBloc(),
//   ),
//   BlocProvider<BookingBloc>(
//     builder: (BuildContext context) => BookingBloc(
//       bookingService:
//       RepositoryProvider.of<RepositoryBundle>(context).booking,
//     ),
//   ),
//   BlocProvider<LocalizationBloc>(
//     builder: (BuildContext context) => LocalizationBloc(),
//   ),
// ];
