import http from "../utils/http-common";
class CategoryDataService {
  getAll() {
    return http.get("/category");
  }
  create(data) {
    return http.post("/category", data);
  }
  delete(id) {
    return http.delete(`/category/${id}`);
  }
}
export default new CategoryDataService();
