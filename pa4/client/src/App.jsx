import React, { useMemo } from "react";
import { Switch, Route, Redirect, useHistory, useLocation } from "react-router-dom";
import "antd/dist/antd.css";
import { Layout, Menu, Avatar } from "antd";
import Home from "./pages/home";
import CateGory from "./pages/category";
import Goods from "./pages/goods";
import Detail from './pages/detail';
import "./App.css";
const { Header, Content } = Layout;
const App = () => {
  const history = useHistory();
  const location = useLocation();
  const selectKey = useMemo(() => {
    const { pathname } = location;
    if (pathname.includes("home")) {
      return "home";
    }
    if (pathname.includes("goods")) {
      return "goods";
    }
    if (pathname.includes("category")) {
      return "category";
    }
    return "home";
  }, [location]);
  return (
    <Layout>
      <Header>
        <h1 className="logo">BD</h1>
        <Menu
          theme="dark"
          mode="horizontal"
          defaultSelectedKeys={[selectKey]}
          onClick={({ key }) => {
            history.push(`/${key}`);
          }}
          items={[
            {
              key: "home",
              label: "Home",
            },
            {
              key: "goods",
              label: "Products",
            },
            {
              key: "category",
              label: "Category",
            },
          ]}
        />
        <div style={{ lineHeight: `50px` }}>
          <Avatar
            style={{ backgroundColor: "#f56a00", verticalAlign: "middle" }}
            size={34}
            gap={3}
          >
            U
          </Avatar>
        </div>
      </Header>
      <Content>
        <Switch>
          <Redirect from="/" to="/home" exact />
          <Route exact path={"/home"} component={Home} />
          <Route exact path="/goods" component={Goods} />
          <Route exact path="/good/:id" component={Detail} />
          <Route exact path="/category" component={CateGory} />
        </Switch>
      </Content>
    </Layout>
  );
};

export default App;
