const category = require("../controllers/category.controller");
module.exports = app => {
  const router = require("express").Router();
  // Create a new User
  router.post("/", category.create);
  // Retrieve all User
  router.get("/", category.findAll);
  router.delete("/:id", category.delete);
  app.use('/api/category', router);
};
