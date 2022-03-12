import 'package:commons/commons.dart';
import 'package:http_interceptor/http/interceptor_contract.dart';
import 'package:http_interceptor/models/request_data.dart';
import 'package:http_interceptor/models/response_data.dart';

class AuthTokenInterceptor implements InterceptorContract {

  final AuthSessionStorage _authSessionStorage = AuthSessionStorage();

  @override
  Future<RequestData> interceptRequest({required RequestData data}) async {
    String? token = await _authSessionStorage.getToken();
    if (token != null) {
      data.headers["Authorization"] = "Bearer $token";
    }
    print(data.toString());
    return data;
  }

  @override
  Future<ResponseData> interceptResponse({required ResponseData data}) async => data;

}