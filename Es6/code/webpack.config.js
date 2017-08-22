module.exports = {
  entry: ['babel-polyfill','./app/index.js'],
  output: {
    path: __dirname + '/build',
    filename: 'bundle.js'
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /(node_modules)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['es2017']
          }
        }
      }
    ]
  },
  devServer: {
    port: 3000,
    contentBase: __dirname + '/build',
    inline: true
  }
}
