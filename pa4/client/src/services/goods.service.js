import http from "../utils/http-common";
import qs from 'qs';
class GoodsDataService {
  getAll(params = {}) {
    return http.get(`/goods?${qs.stringify(params)}`);
  }

  get(id) {
    return http.get(`/goods/${id}`);
  }

  create(data) {
    return http.post("/goods", data);
  }

  update(id, data) {
    return http.put(`/goods/${id}`, data);
  }

  delete(id) {
    return http.delete(`/goods/${id}`);
  }

  deleteAll() {
    return http.delete(`/goods`);
  }

  findByTitle(title) {
    return http.get(`/goods?title=${title}`);
  }
}

export default new GoodsDataService();


// categoryId: "49cfecb7-76c0-443a-86d7-2d8129aa1a8b"
// price: 2
// title: "10120"