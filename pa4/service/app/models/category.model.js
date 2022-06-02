module.exports = (sequelize, Sequelize) => {
  const DataTypes = Sequelize.DataTypes;
  const Category = sequelize.define("category", {
    id: {
      primaryKey: true,
      type: Sequelize.UUID,
      defaultValue: DataTypes.UUIDV4,
      allowNull: false,
      comment: "id"
    },
    name: {
      type: Sequelize.STRING,
      comment: "分类名称",
    },
  });

  return Category;
};
