const goods = require("../controllers/goods.controller");
module.exports = app => {
  const router = require("express").Router();

  // Create a new Goods
  router.post("/", goods.create);

  // Retrieve all Goods
  router.get("/", goods.findAll);

  // Retrieve all published Goods
  router.get("/published", goods.findAllPublished);

  // Retrieve a single Goods with id
  router.get("/:id", goods.findOne);

  // Update a Goods with id
  router.put("/:id", goods.update);

  // Delete a Goods with id
  router.delete("/:id", goods.delete);

  // Delete all Goods
  router.delete("/", goods.deleteAll);

  app.use('/api/goods', router);
};
