import React, { useEffect, useState } from "react";
import UserDataService from "../../services/users.service";
import { Table } from "antd";
import formatData from "../../utils/formatDate";
import "./index.css";

const User = () => {
  const [dataSource, setDataSource] = useState([]);
  useEffect(() => {
    UserDataService.getAll().then((res) => {
      setDataSource(res.data);
    });
  }, []);
  const columns = [
    {
      title: "序号",
      render: (text, round, index) => {
        return <span>{index + 1}</span>;
      },
      width: 55,
      align: "center",
      key: "id",
    },
    {
      title: "姓名",
      dataIndex: "username",
      key: "username",
    },
    {
      title: "邮箱",
      dataIndex: "email",
      key: "email",
    },
    {
      title: "创建时间",
      dataIndex: "createdAt",
      key: "createdAt",
      render: (text) => {
        return formatData(text);
      },
    },
    {
      title: "更新时间",
      dataIndex: "updatedAt",
      key: "updatedAt",
      render: (text) => {
        return formatData(text);
      },
    },
  ];
  return (
    <section className="container">
      <Table
        rowKey="id"
        size="small"
        dataSource={dataSource}
        columns={columns}
      />
    </section>
  );
};

export default User;
