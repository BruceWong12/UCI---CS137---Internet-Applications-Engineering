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
      comment: "标题",
    },
    imgs: {
      type: Sequelize.STRING,
      comment: "图片",
    },
    category: {
      type: Sequelize.STRING(128),
      comment: "分类",
    },
    categoryId: {
      type: Sequelize.STRING(128),
      comment: "分类ID",
    },
    user: {
      type: Sequelize.STRING,
      comment: "作者",
    },
    userId: {
      type: Sequelize.STRING,
      comment: "作者ID",
    },
    description: {
      type: Sequelize.STRING,
      comment: "描述",
    },
    price: {
      type: Sequelize.DOUBLE,
      comment: "价格",
    },
    number: {
      type: Sequelize.BIGINT,
      comment: "数量",
    },
    published: {
      type: Sequelize.BOOLEAN,
      comment: "是否发布",
      defaultValue: false
    }
  });

  return Goods;
};
