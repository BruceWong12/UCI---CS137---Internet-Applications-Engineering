const users = require("../controllers/user.controller");
module.exports = app => {
  const router = require("express").Router();
  // Create a new User
  router.post("/", users.create);
  // Retrieve all User
  router.get("/", users.findAll);
  app.use('/api/users', router);
};
