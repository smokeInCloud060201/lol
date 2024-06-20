const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');

module.exports = {
    entry: './src/AppForm.tsx',
    mode: 'development',
    devServer: {
        hot: true,
        port: 8080,
        historyApiFallback: true
    },
    output: {
        filename: 'bundle.[fullhash].js',
        path: path.resolve(__dirname, '../src/main/resources/static')
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: '../src/main/resources/static/index.html'
        })
    ],
    resolve: {
        modules: [__dirname, 'src', 'node_modules'],
        extensions: ['*', '.js', '.jsx', '.tsx', '.ts']
    },
    module: {
        rules: [
            {
                test: /\.(js|ts)x?$/,
                exclude: /node_modules/,
                use: ['babel-loader']
            },
            {
                test: /\.scss$/,
                use: ['style-loader', 'css-loader', 'postcss-loader', 'sass-loader']
            },
            {
                test: /\.(png|svg|jpg|gif)$/,
                exclude: /node_modules/,
                use: ['file-loader']
            }
        ]
    }
};
