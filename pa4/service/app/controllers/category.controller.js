const db = require("../models");
const Category = db.category;
// Create and Save a new Category
exports.create = (req, res) => {
  const { name } = req.body
  // Validate request
  if (!name) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create a Category
  const params = {
    name
  };

  // Save Category in the database
  Category.create(params)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Category."
      });
    });
};

exports.findAll = (req, res) => {
  Category.findAll()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving Category."
      });
    });
};

exports.delete = (req, res) => {
  const id = req.params.id;

  Category.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "Goods was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete Goods with id=${id}. Maybe Goods was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not delete Goods with id=" + id
      });
    });
};