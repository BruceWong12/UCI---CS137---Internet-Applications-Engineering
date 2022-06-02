module.exports = (sequelize, Sequelize) => {
  const DataTypes = Sequelize.DataTypes;
  const Goods = sequelize.define("goods", {
    id: {
      primaryKey: true,
      type: Sequelize.UUID,
      defaultValue: DataTypes.UUIDV4,
      allowNull: false,
      comment: "id"
    },
    title: {
      type: Sequelize.STRING,
      comment: "Title",
    },
    imgs: {
      type: Sequelize.STRING,
      comment: "Image",
    },
    category: {
      type: Sequelize.STRING(128),
      comment: "Category",
    },
    categoryId: {
      type: Sequelize.STRING(128),
      comment: "Category ID",
    },

    description: {
      type: Sequelize.STRING,
      comment: "Description",
    },
    price: {
      type: Sequelize.DOUBLE,
      comment: "Price",
    },
    number: {
      type: Sequelize.BIGINT,
      comment: "Quantity",
    },
    published: {
      type: Sequelize.BOOLEAN,
      comment: "Published",
      defaultValue: false
    }
  });

  return Goods;
};
