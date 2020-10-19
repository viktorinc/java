import React, {Component} from 'react';
import {Text, View, Alert} from 'react-native';
import { Button } from 'react-native-elements';
class HomeScrean extends Component {
    state = {  }
    render() { 
        const {navigate} = this.props.navigation;
        return (
            <View>
                <Text>Home page</Text>
                <Button title="Привіт" 
                    type="outline" 
                    onPress={()=>Alert.alert("Повідомлення","Будьмо уважні і обережні")}
                    size={15} />
                <Button title="Вхід" onPress={()=>navigate("Login")} />
            </View>

         );
    }
}
 
export default HomeScrean;