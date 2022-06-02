const db = require("../models");
const Goods = db.goods;
const Op = db.Sequelize.Op;

// Create and Save a new goods
exports.create = (req, res) => {
  const { title, imgs, category, categoryId, description, price, number, published } = req.body
  // Validate request
  if (!title) {
    res.status(400).send({
      message: "Title can not be empty!"
    });
    return;
  }

  // Create a goods
  const params = {
    title,
    imgs,
    category,
    categoryId,
    description,
    price,
    number,
    published
  };

  // Save goods in the database
  Goods.create(params)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the goods."
      });
    });
};

// Retrieve all goods from the database.
exports.findAll = (req, res) => {
  let condition = null
  if (Object.keys(req.query).length) {
    condition = Object.keys(req.query).reduce((pre, item) => {
      if (req.query[item]) {
        switch(item) {
          case 'title':
            pre[item] = { [Op.like]: `%${req.query[item]}%` }
            break
          case 'categoryId':
            pre[item] = req.query[item]
            break
          case 'price':
            if (req.query[item] === '1') {
              // 0 ~ 1000
              pre[item] = { [Op.gte]: 0, [Op.lte]: 1000 }
            }
            if (req.query[item] === '2') {
              // 1000 ~ 5000
              pre[item] = { [Op.gte]: 1000, [Op.lte]: 5000 }
            }
            if (req.query[item] === '3') {
              // 5000 ~ 10000
              pre[item] = { [Op.gte]: 5000, [Op.lte]: 10000 }
            }
            if (req.query[item] === '4') {
              // >= 10000
              pre[item] = { [Op.lte]: 10000 }
            }
            break
        }
      }
      return pre
    }, {})
  }
  Goods.findAll({ where: condition })
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving goods."
      });
    });
};

// Find a single Goods with an id
exports.findOne = (req, res) => {
  const id = req.params.id;

  Goods.findByPk(id)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message: "Error retrieving Goods with id=" + id
      });
    });
};

// Update a Goods by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  Goods.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "Goods was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update Goods with id=${id}. Maybe Goods was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating Goods with id=" + id
      });
    });
};

// Delete a Goods with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  Goods.destroy({
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

// Delete all Goods from the database.
exports.deleteAll = (req, res) => {
  Goods.destroy({
    where: {},
    truncate: false
  })
    .then(nums => {
      res.send({ message: `${nums} Goods were deleted successfully!` });
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all Goods."
      });
    });
};

// find all published goods
exports.findAllPublished = (req, res) => {
  Goods.findAll({ where: { published: true } })
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving goods."
      });
    });
};
