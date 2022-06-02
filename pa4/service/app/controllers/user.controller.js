const db = require("../models");
const User = db.user;
// Create and Save a new User
exports.create = (req, res) => {
  const { username, password, email } = req.body
  // Validate request
  if (!username || !password) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create a User
  const params = {
    username, password, email
  };

  // Save User in the database
  User.create(params)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the user."
      });
    });
};

exports.findAll = (req, res) => {
  User.findAll()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving user."
      });
    });
};