/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, {Component} from 'react';
import {createStackNavigator} from 'react-navigation-stack';
import {createAppContainer} from 'react-navigation';
import { createDrawerNavigator } from '@react-navigation/drawer';
import { NavigationContainer } from '@react-navigation/native';
import HomeScrean from './components/Home/HomeScrean';
import {DrawerContent} from "./components/CustomElements/DrawerContent";
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  View,
  Text,
  StatusBar,
} from 'react-native';

import {
  Header,
  LearnMoreLinks,
  Colors,
  DebugInstructions,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';
import LoginScreen from './components/Account/Login/LoginScreen';

const MainNavigator = createStackNavigator(
  {
    Home: {screen: HomeScrean},
    Login: {screen: LoginScreen},
 
  },
  {
    initialRouteName: 'Home',
    navigationOptions: {
      headerStyle: {
        backgroundColor: '#777777',
      },
      headerTintColor: '#fff',
      headerTitleStyle: {
        fontWeight: 'bold',
      },
    },
  },
);

const Drawer = createDrawerNavigator();
const RootContainer = createAppContainer(MainNavigator);


class App extends React.Component {
  render() {
    return (
      <NavigationContainer>
        <Drawer.Navigator  drawerContent={props => <DrawerContent {...props} />} >
          <Drawer.Screen name="Home" component={HomeScrean} />
          <Drawer.Screen name="Login" component={LoginScreen} />
        </Drawer.Navigator>
      </NavigationContainer>
    );
  }
}

export default App;
