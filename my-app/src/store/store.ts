// store.ts
import { configureStore } from '@reduxjs/toolkit';
import createSagaMiddleware from 'redux-saga';
import fileReducer from '../redux/fileSlice';
import fileSaga from '../saga/fileSaga';

const sagaMiddleware = createSagaMiddleware();

const store = configureStore({
  reducer: {
    file: fileReducer,
  },
  middleware: [sagaMiddleware],
});

sagaMiddleware.run(fileSaga);

export default store;
