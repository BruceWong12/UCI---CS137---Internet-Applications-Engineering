module.exports = (sequelize, Sequelize) => {
  const DataTypes = Sequelize.DataTypes;
  const User = sequelize.define("user", {
    id: {
      primaryKey: true,
      type: Sequelize.UUID,
      defaultValue: DataTypes.UUIDV4,
      allowNull: false,
      comment: "id"
    },
    username: {
      type: Sequelize.STRING,
      comment: "用户名",
    },
    password: {
      type: Sequelize.STRING,
      comment: "密码",
    },
    email: {
      type: Sequelize.STRING,
      comment: "邮箱",
    }
  });

  return User;
};
