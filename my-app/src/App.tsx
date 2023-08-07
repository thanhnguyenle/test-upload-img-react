// App.tsx
import React from 'react';
import { Provider } from 'react-redux';
import store from './store/store';
import FileComponent from './component/FileComponent';

const App = () => {
  return (
    <Provider store={store}>
      <div className="App">
        <FileComponent />
      </div>
    </Provider>
  );
};

export default App;